##Taskmaster
An application to track tasks, allowing the user to see a description of the task and its current status

###Deployment
[Taskmaster Deployment](taskmastereb-dev.us-west-2.elasticbeanstalk.com)

###Routes
    /tasks            - GET
    /tasks            - POST with body parameters for title and description of task
    /tasks/{id}/state - PUT allows user to advance status of a task

###Issues

