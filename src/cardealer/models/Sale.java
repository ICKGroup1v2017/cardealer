/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cardealer.models;

/**
 *
 * @author ahmet
 */
public class Sale implements Comparable<Sale> {
    private Long id;

    @Override
    public int compareTo(Sale o) {
        return this.id.compareTo(o.id);
    }
}
