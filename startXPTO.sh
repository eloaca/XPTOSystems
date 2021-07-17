#!/bin/bash

AMBIENTE=""
DEBUG="-agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=5005"
RUN="mvn spring-boot:run"

echo "Selecione o ambiente que ira rodar a aplicacao: tst ou prod (Padrão: tst)"     
read AMBIENTE

if [ -z $AMBIENTE ]; then
	AMBIENTE=tst
fi

echo "Subindo a aplicacao no ambiente: $AMBIENTE"

$RUN -Dspring.profiles.active=$AMBIENTE -Dspring-boot.run.jvmArguments=$DEBUG

echo "Tchau :)"