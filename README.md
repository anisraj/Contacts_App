# Contacts_App

## Architecture
In Contacts_App we are following MVVM architecture

## Code Structure
1) Project developed entirely in Kotlin
2) Dagger -
    Dagger used for injecting ViewModel Factory dependency
3) Data Binding -
    Used data binding, from XML called ViewModel's functions
4) LiveData -
    Used LiveData for getting contacts data from ViewModel.
    Also in ContactDetailsActivity used LiveData for listening UI actions using ENUM ,
    which are called from XML through data binding.
    Observed LiveData from view
5) Animation -
    360 degree rotation animation added for refresh button

## Features of the app
1) Displays all Contacts
2) Display Contacts with their sort character on top
3) We can Search contacts
4) We can refresh contacts by clicking refresh button
5) In details page , if user has photo, then we can see it, if not then their is default image
6) We can call and message that contact from details page
7) After search if no contact found then shows message

## ScreenShots
![Screenshot_2021-11-10-14-49-16-209_com example mycontacts 1](https://user-images.githubusercontent.com/39364582/141089452-72627755-cd2d-4cef-b576-31ef7283f9b8.jpg)

![Screenshot_2021-11-10-14-49-29-559_com example mycontacts 1](https://user-images.githubusercontent.com/39364582/141089544-04c7c8c5-ece4-45a2-b310-b35ccb6c0c2c.jpg)

![Screenshot_2021-11-10-14-49-41-113_com example mycontacts 1](https://user-images.githubusercontent.com/39364582/141089647-aa7a656c-3da1-4894-846a-b86c8c540d5c.jpg)

![Screenshot_2021-11-10-14-49-55-697_com example mycontacts 1](https://user-images.githubusercontent.com/39364582/141089719-25ad32c9-380a-4f63-8a44-3c1d452939aa.jpg)
