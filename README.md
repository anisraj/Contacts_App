# Contacts_App

## Architecture
In Contacts_App we are following MVVM architecture

## Code Structure
1) Project developed entirely in Kotlin
2) Dagger
    Dagger used for injecting ViewModel Factory dependency
3) Data Binding
    Used data binding, from XML called ViewModel's functions
4) LiveData
    Used LiveData for getting contacts data from ViewModel.
    Also in ContactDetailsActivity used LiveData for listening UI action methods ,
    which are called from XML through data binding.
    Observed LiveData from view

## Features of the app
1) Displays all Contacts
2) Display Contacts with their sort character on top
3) We can Search contacts
4) We can refresh contacts by clicking refresh button
5) In details page , if user has photo, then we can see it, if not then their is default image
6) We can call and message that contact from details page
7) After search if no contact found then shows message

## ScreenShots
![Screenshot_2021-11-10-14-49-55-697_com example mycontacts 1](https://user-images.githubusercontent.com/39364582/141087485-df3a501e-e550-44bd-8dc5-7efb480361b0.jpg)
