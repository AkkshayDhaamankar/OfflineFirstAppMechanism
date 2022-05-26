# OfflineFirstAppMechanism
Developing offline first app mechanism using android architecture(Room, WorkManager, Navigation, View Binding, View Model,etc), Dagger-Hilt, Firestore Cloud DB, Coroutines, Clean Architecture using MVVM, Multi-Module Setup and foundation(Android KTX) components.

# Libraries Used :- 
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
