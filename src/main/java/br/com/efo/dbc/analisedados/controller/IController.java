package br.com.efo.dbc.analisedados.controller;

import java.io.File;
import java.io.FileNotFoundException;

public interface IController {

    void execute(final File file) throws FileNotFoundException;

}
