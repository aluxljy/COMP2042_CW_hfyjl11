# COMP2042_CW_hfyjl11
1. Project Title: Brick Destroyer (COMP2042 Software Maintenance Coursework)
2. Project Description: In this project I have maintained the existing code and extended the game, which is a re-implementation of a classic game called Brick Breaker. The refactored, maintained and extended version is called the Brick Destroyer which I added several new levels and elements to make the game more interesting. More details on the work done will be included below.
3. Table of Content:
- How to install / use the project
- Work conducted
- Explanation on the functions of the game
- How to play

How to install / use the project:
- Since JavaFX was used in this project, the configurations should be properly setup if it hasn't already.
- Also another external library called org.json is needed to be included in the project structure to be able to perform tasks that involve json.
- First, create a new project in your desired IDE and copy the source files into your project.
- Then, add the javafx-sdk17.0.1\lib and the org.json to the project structure if they haven't already been added.
- Lastly, edit the configuration by adding the VM option, --module-path %MODULE PATH% --add-modules=javafx.controls,javafx.fxml,javafx.swing e.g.(--module-path /Users/Sample/Downloads/javafx-sdk-17.0.1/lib --add-modules=javafx.controls,javafx.fxml,javafx.swing)

Work conducted:
- Basic maintenance such as  renaming classes, encapsulating the variables by changing them to private, deleting unused resources, removing dead and duplicated codes, enhancing the identifier naming, reducing the method size have been performed.
- Some codes have also been splitted code into components to enhance component encapsulation and reduce coupling, for example separating the Crack class from the Brick class and changing public methods that are not used in other classes to private methods.
- Design patterns such as Factory Pattern was implemented to create Ball objects and Brick objects and also MVC for the entire project.
- Also, some bugs in which when initially setting the speed of the ball, the ball would not be released and would roll over the player were solved by adjusting the position of the player.
- Used JavaFX to make the HIGHSCORE page, designed the layout using Scene Builder while other pages remained using Java Swing and stored the highscores in json

Explanation on the functions of the game:
- Throughout the game there will be 8 different levels in total in which every level has different combinations of bricks.
- There will be 2 types of balls in the game, first the rubber ball which is bigger in size slower in speed will appear in the first 5 levels of the game and second the super ball which is smaller in size faster in speed will appear in the last 3 levels of the game.
- By pressing the ESC key on your keyboard, the PAUSE MENU will be displayed and three buttons can be accessed which are the CONTINUE button to continue the game, RESTART button to restart the game and BACK TO MENU button to go back to the menu.
- There are 2 modes, the first is "TRAINING" mode, in this mode, you can press alt + shift + F1 / fn + alt + shift + F1 on your keyboard to view the DEBUG CONSOLE to skip levels, adjust the ball speed and reset the balls.
- The second is "RANKED" mode, there is a timer in this mode, you will need to complete as much levels as possible and get higher score within the time limit.
- In "RANKED" mode, breaking a clay brick (brown coloured) awards 1 score, cement brick (dark gray coloured) awards 2 scores, steel brick (light gray coloured) awards 4 scores and magic brick (blue coloured) randomly awards an increment or decrement of 20 scores.
- By the end of the game, you will be able to submit your name if your score makes it into the top 6 of the HIGH SCORES list.
- On the MENU page, you will be able to access the INFO page which tells you about the instructions on playing the game as well as the HIGH SCORES page which allows you to view the current top 6 high scores.

How to play:
- Press the key "A" on your keyboard to move the paddle to the left and "D" to move the paddle to the right.
- Press the space bar on your keyboard to start or pause the game.
- Make sure to catch the ball using the paddle, you will only be given 3 balls, if all balls are used up then game over.

Acknowledgement / Credit Author: Credits to the author Filippo Ranza, created by on 04/09/16, Brick Destroy - A simple Arcade video game Copyright (C) 2017 Filippo Ranza
