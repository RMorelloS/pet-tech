package br.com.fiap.demo.produto.service.exception;

import javax.sound.midi.ControllerEventListener;

public class ControllerNotFoundException extends RuntimeException
{
    public ControllerNotFoundException(String message){
        super(message);
    }
}
