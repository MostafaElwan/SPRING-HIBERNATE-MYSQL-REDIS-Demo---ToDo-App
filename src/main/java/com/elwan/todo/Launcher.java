package com.elwan.todo;

import java.util.List;

import com.elwan.todo.common.AppConstant;
import com.elwan.todo.common.AppLogger;
import com.elwan.todo.common.ConfigManager;
import com.elwan.todo.delegate.TodoDelegate;
import com.elwan.todo.exception.APIException;
import com.elwan.todo.model.Todo;
import com.elwan.todo.model.User;

public class Launcher {
	
	private static final AppLogger logger = new AppLogger(Launcher.class);
	
    public static void main(String[] args) {
    	
    	try {
    		ConfigManager config = ConfigManager.getInstance();
    		config.loadProperties(AppConstant.Settings.CONFIG_FILE_PATH);
    		config.initializeSpring();
    		
            TodoDelegate d = config.getBean(TodoDelegate.class);
            
            int counter = 62;
            
            List<User> users = d.getAllUsers();
            
            User u = new User();
            u.setUsername("melwan");
            u.setPassword("melwan" + counter);
            u.setName("Mostafa Elwan");
            u.setEmail("mostafa.elwan"+counter+"@etisalat.com");
            
            d.createUser(u);
            
            Todo td = new Todo();
            td.setTitle("Sample Todo");
            td.setDescription("This is the description of the sample todo");
            
            d.createTodo(u, td);
            
            List<Todo> todoList = d.getTodoList(u);
            
    	} catch(APIException e) {
    		logger.error("Application has been stopped due to error: ", e);
    	}
    }
}
