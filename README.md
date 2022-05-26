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


