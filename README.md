**Poplar Actors** App to show/search popular actors with (poster - name - bio - date of birth - place of birth - photo album), User can open each image in the photo album and download it to his phone gallery.

## Getting Started

These instructions will get you a copy of the project up and running on your local machine for development and testing purposes. See deployment for notes on how to deploy the project on a live system.

### Installing

* Clone the repo.

```
cd PoplarActors
```
### Running the application

* The application runs on [Android Studio](https://developer.android.com/studio/)
* Follow [this link](https://developer.android.com/studio/run/) to build and run the app.

### Android Architecture

*This is app is build based on MVP architecture with some modification toward the clean architecture which was inspired by uncle BOB while using DI and Rx .

### Project Structure

#### The app has following packages:
1. **data**: It contains all the data accessing and manipulating components(Network - Database "if needed" - model).
2. **di**: Dependency providing classes using Dagger2.
3. **ui**: View classes along with their corresponding Presenters.
4. **utils**: Utility classes.
#### The app used the following Thirdparties:
1. **DI**: Dagger2 .
2. **Rx**: rxjava2 with RxAndroid .
3. **Network**: Retrofit with rxJava2Adapter and using the caching feature of retrofit.
4. **Other**: used other libraries and all of them are on libraries.gradle.