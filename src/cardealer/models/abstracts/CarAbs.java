/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cardealer.models.abstracts;

import cardealer.enums.Direction;
 
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author ahmet
 */
public abstract class CarAbs {
    
    
    /**
     * The default car movement
     */
    public Direction direction = Direction.PARKING;
    
    /**
     * The movement speed
     */
    public float speed=0;
    
    
    
    public abstract void move(float speed);
}
