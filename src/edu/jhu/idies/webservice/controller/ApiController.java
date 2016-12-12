package edu.jhu.idies.webservice.controller;

import javax.servlet.ServletContext;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import edu.jhu.idies.webservice.model.ErrorMessage;


@RestController
public class ApiController {
    private static final Logger LOG = LogManager.getLogger(ApiController.class);
    
    @Autowired
    ServletContext context;
    
    @RequestMapping(value="/api/hello/{name}", method = RequestMethod.GET)
    public String hello(@PathVariable String name) throws Exception {
        return String.format("Hello, %s!", name);
    }
    
    @RequestMapping(value="/api/error", method = RequestMethod.GET)
    public String error() throws Exception {
        throw new Exception("An error has occured");
    }
    
    @ExceptionHandler(Exception.class)
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public Object handleException(Exception e) {
        return new ErrorMessage(e.getMessage());
    }
}
