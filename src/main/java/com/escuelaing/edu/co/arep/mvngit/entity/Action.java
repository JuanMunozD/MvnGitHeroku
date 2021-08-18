/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.escuelaing.edu.co.arep.mvngit.entity;

/**
 *
 * @author Juan Sebastian
 */
public class Action {
    String name;

    public Action(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Accion{" + "name=" + name + '}';
    }
    
}
