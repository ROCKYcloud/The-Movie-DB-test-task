# The-Movie-DB-test-task
Test Task for android candidates

Write the application that allows user to review the list of movies and add them to favorites. You should provide your solution as a link to public repository. Please make commits of logical units, so we can evaluate your progress and add comments as you see fit.

Functional requirements:
The main screen contains two tabs: All and Favorites.
Tab All should display a list of movies with pagination. Movies should be grouped by month (take a look at design example)
Tab Favorites should display a list of bookmarked movies.
User can add / remove movie to / from his favorites. All changes should be visible immediately on both tabs. Bookmarks can be saved localy (without api call).
User can refresh movies list using pull-to-refresh.
User can see early loaded content without internet connection (only 1st page, without pagination)
Movies list filter:

vote average: 7+
vote count: 100+
sorted by primary release date, the newest movies should appear at the top of list
Screen UI should display one of the following states:

Loading
Refreshing
Loading More (pagination)
Error
Content (just showing movies)
Optional Requirements
Implement sign-in with Google or Facebook
Implement movie sharing via any existing provider
Technical requirements
Kotlin is required
Sqlite / Room is required
One of MVVM / MVI / Redux architecture is required;
Clean Architecture is required;
Dependency injection is required;
Jetpack Compose is required
Unit-tests are required;
Notes
Your code should be testable, scalable and flexible
The most important parts of logic should be covered with unit tests (you are free to decide what parts)
You are free to use any libraries
You are free to implement your own design
Make sure that anyone can build your project.
Design example
Here is an example how application might look like, but the final decision is up to you.

Design Example

Resources
The movies database:

docs: https://developer.themoviedb.org/docs
api method: https://developer.themoviedb.org/reference/discover-movie
Please, obtain your own API key.

Design example

P.S.
