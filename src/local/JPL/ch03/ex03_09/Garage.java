/*
 * Copyright(C) 2013 Yutaka Kato
 */
package local.JPL.ch03.ex03_09;

import java.util.ArrayList;
import java.util.List;

/**
 * くるまを入れる倉庫
 */
public class Garage implements Cloneable {

    private static final int CAPACITY = 3;
    private List<Vehicle> vehicles = new ArrayList<Vehicle>();

    public static void main(String[] args) throws CloneNotSupportedException {
        Garage garage = new Garage();
        garage.add(new Vehicle("Car 1"));
        garage.add(new Vehicle("Car 2"));
        garage.add(new Vehicle("Car 3"));
        garage.printVehicleList();
        Garage garage2 = garage.clone();
        garage2.printVehicleList();
    }

    /**
     * 倉庫にくるまを入れます。
     * 
     * @param vehicle くるま
     * @return 入ったら true, 入らなかったら false
     */
    public boolean add(Vehicle vehicle) {
        if (vehicles.size() == CAPACITY) {
            return false;
        }
        return vehicles.add(vehicle);
    }

    /**
     * 倉庫からくるまを取り出します。
     * 
     * @param id くるま番号
     * @return くるま, 見つからなかったときは null
     */
    public Vehicle remove(int id) {
        for (Vehicle v : vehicles) {
            if (v.getId() == id) {
                vehicles.remove(v);
                return v;
            }
        }
        return null;
    }

    /**
     * くるま一覧を表示します。
     */
    public void printVehicleList() {
        for (Vehicle v : vehicles) {
            System.out.println(v);
        }
    }

    @Override
    public Garage clone() throws CloneNotSupportedException {
        // return (Garage) super.clone(); // これだと車のIDが重複する
        Garage clone = new Garage();
        for (Vehicle v : vehicles)
            clone.add(v.clone());
        return clone;
    }
}
