/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cardealer.database.exceptions;

/**
 *
 * @author ahmet
 */
public class EntityExistsException extends Exception {
    public EntityExistsException(){
        super("Entity Exists Exception");
    }
}
