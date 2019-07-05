##Taskmaster
An application to track tasks, allowing the user to see a description of the task and its current status

###Deployment
[Taskmaster Deployment](taskmastereb-dev.us-west-2.elasticbeanstalk.com)

###Routes
    /tasks            - GET
    /tasks            - POST with body parameters for title,description, status(matching assignee status), and if present assignee
    /tasks/{id}/state - PUT allows user to advance status of a task
    /tasks/{id}       - DELETE delete a task with the given id
    /users/{name}/tasks?status=assigned
                      - GET receive JSON data for all of the tasks assigned to that user that are not Assigned
    /tasks?status=available
                      - GET receive data of all Available tasks
     
                      

###Issues
Updated actions of routes from Lab26 to Lab27 is in process

