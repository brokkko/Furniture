package com.company.Furniture.storage;

import com.company.Furniture.ApplicationController;

import java.util.logging.Level;
import java.util.logging.Logger;

public class FileNotFoundException extends StorageException {
    private final Logger LOG = Logger.getLogger(ApplicationController.class.getName());

    public FileNotFoundException(String message) {
        super(message);
        LOG.log(Level.WARNING, "File not found: ");
    }

    public FileNotFoundException(String message, Throwable cause) {
        super(message, cause);
        LOG.log(Level.WARNING, "File not found: ");
    }
}
