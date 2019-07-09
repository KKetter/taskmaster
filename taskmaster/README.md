##Taskmaster
An application to track tasks, allowing the user to see a description of the task, an image associated with the task,
 and its current status.

###Deployment
[Taskmaster Back End Deployment](taskmastereb-dev.us-west-2.elasticbeanstalk.com)

###Routes
    /tasks            - GET
    /tasks/{id}       - GET should include image url associated with that image
    /users/{name}/tasks?status=assigned
                      - GET receive JSON data for all of the tasks assigned to that user that are not Assigned
    /tasks?status=available
                      - GET receive data of all Available tasks
    /tasks            - POST with body parameters for title,description, status(matching assignee status), and if present assignee
    /tasks/{id}/images
                      - POST upload images that are associated with tasks
    /tasks/{id}/state - PUT allows user to advance status of a task
    /tasks/{id}       - DELETE delete a task with the given id
                      
     
                      

###Issues
Completed through Lab28
TO DO: 
    GET  /tasks/{id} should include image url
    POST /tasks/{id}/images

