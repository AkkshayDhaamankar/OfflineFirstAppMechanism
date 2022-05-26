# OfflineFirstAppMechanism
Developing offline first app mechanism using android architecture(Room, WorkManager, Navigation, View Binding, View Model,etc), Dagger-Hilt, Firestore Cloud DB, Coroutines, Clean Architecture using MVVM, Multi-Module Setup and foundation(Android KTX) components.

# Libraries Used
- Database
  - Room DB for local caching.
  - Firestore for cloud DB storage

- Foreground and Background Sync Handling
  - WorkManager -> Workmanager used along with coroutine (CoroutineWorker) and Dagger-Hilt (HiltWorker)

- Dependency Injection
  - Dagger Hilt

- Routing
  - Navigation
  - Arguments passed between screens using navigation 

- Communication between app layers
  - Kotlin Coroutines for interacting between ViewModel, UseCases and Room DB
  - Broadcast receiver for receiving updates when work manager finishes syncing a job.

Other 
  - ViewBinding
  - Cleam Architecture using MVVM
  - Multi-Module Setup -> Network and Core module are completely independent of each other. App module depends on Network and Core module
  - Kotlin Coroutines
  - Android KTX

# Single Source of Truth
I have used two ways to handle scenario when syncing of a comment fails (can use either of below by uncommenting the commented way).
  1.  Delete comment from local db directly if syncing fails to maintain data integrity between local and remote DB.
  2.  Change status of isSync variable (0 -> not synced, 1 -> synced, 2 -> failed to sync) which will change color of comment text to red and also add a strike over the text.

# Architecture Used 
![architecture](https://user-images.githubusercontent.com/25646373/170555382-58736790-6e58-46c7-96ca-1de20b1b65d8.png)

# Offline Sync Scenarios
Note :- Green Color -> Comment is synced, Grey Color -> Comment is yet to be synced, Red Color with strike -> Comment failed to sync
  - App running, no sync failure, internet available

# App Screenshots

<img width="377" alt="Screenshot 2022-05-27 at 12 18 36 AM" src="https://user-images.githubusercontent.com/25646373/170556358-1cbc1767-f8cc-4aca-9405-af0edd09f377.png">
<img width="377" alt="Screenshot 2022-05-27 at 12 18 49 AM" src="https://user-images.githubusercontent.com/25646373/170556397-647f5c2f-060d-4965-9e3a-330b6f11a3ef.png">
<img width="377" alt="Screenshot 2022-05-27 at 12 19 00 AM" src="https://user-images.githubusercontent.com/25646373/170556418-7512e115-5e25-4a6c-a63c-e98eba550a6a.png">


