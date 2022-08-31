package com.app.mygym.util;

import com.app.mygym.entities.RoutineItem;
import com.app.mygym.entities.User;

import java.util.ArrayList;
import java.util.List;

public class MockEntities {

    public static User getSuperUser() {
        return new User("abel.marquez", "abel.marquez@gmail.com", "1234");
    }

    public static List<RoutineItem> mockRoutines(){
        List<RoutineItem> routineItems = new ArrayList<>();
        routineItems.add(new RoutineItem("Rutina de Pierna", "Semana 2", "Abel Marquez"));
        routineItems.add(new RoutineItem("Rutina de Bicep", "Semana 2", "Abel Marquez"));
        routineItems.add(new RoutineItem("Rutina de Tricep", "Semana 2", "Abel Marquez"));
        routineItems.add(new RoutineItem("Rutina de Espalda", "Semana 2", "Abel Marquez"));

        return routineItems;
    }



}
