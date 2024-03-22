# Project structure
The project is implemented using MVVM architecture with Clean Architecture approach. The **_view_** layer is represented by Compose-based UI files. The **_presentation_** layer contains ViewModels, which are communicating with **_domain_** layer via use cases. The repositories serve as an interface between **_domain_** and feature-independent data sources (**_data_** layer). 

There are two screens in the app, which are integrated into a simple Compose-based navigation. The first screen displays a paged list of curated photos from Pexels API. The pull-to-refresh functionality is also implemented. The code for this screen is located in `list` package. The second screen is just a fullscreen view of selected photo. Its code is contained inside `detail` package.

Retrofit library is used for client-server communication. For dependency injection I'm using the classic combination of Dagger and Hilt libraries. Image loading and displaying is handled by Coil for Compose.
