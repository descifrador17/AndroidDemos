package com.descifrador.rxdemo

class DataSource{

    fun createTasksList() : List<Task>{

        val tasks = ArrayList<Task>()
        tasks.add(Task("Take out trash",true,3))
        tasks.add(Task("Walk The Dog",false,2))
        tasks.add(Task("Make The Bed",true,1))
        tasks.add(Task("Unload the dishwasher",false,0))
        tasks.add(Task("Make dinner",true,5))
        tasks.add(Task("Sleep",false,4))
        return tasks;
    }
}