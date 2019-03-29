# Comrades
This is a open, handy, and intuitive file sharing app designed and developed for BITS Goa by Mobile Applications Club .
Educational Institutes generally have student accounts with unlimited Drive storage from Google. So we decided to use this storage and build a file-sharing platform where students can share files(handouts, books, solutions, manuals etc.)  according to the courses taught in their University.
Though Comrades may sound a bit communal, it is Community + grades :P 

# Key Features
1. Creation of courses for all streams and by anyone.
2. Prevents file duplication by hashing files using MD5. (Nobody can upload a file more than once).
3. A proper Timeline which shows files recently added and courses recently created.
4. Subscription to your favourite courses which allows you to receive notifications whenever someone uploads a file.
5. A proper rank system based on number of files uploaded to keep users motivated.
6. Supports Multiple downloads in background.
7. Supports Multiple Uploads in Background.
8. Complete offline support to view or search from already downloaded files.
9. option to delete files uploaded by mistake(specifically for the uplaoder).

# Libraries Used:
This app uses firebase for its backend to store files and courses Information and to send notifications. Most of the things implemented are using firebase-fuctions. Files are stored in the Google Drive Storage of the user who uploaded the file. This app uses Room Persistence Library for offline storage and OkHttp for making requests to Google Drive API to download files. Other Libraries used are Picasso, Firebase Auth, Firebase Messaging, Crashylytics, Chrisbane's PhotoView, Gson and Circular Progress bar.

# Screenshots

<img src="https://raw.githubusercontent.com/aayushsingla/comrades/master/Screenshots/sr01.webp" width="30%"> <img src="https://raw.githubusercontent.com/aayushsingla/comrades/master/Screenshots/sr02.webp" width="30%"> <img src="https://raw.githubusercontent.com/aayushsingla/comrades/master/Screenshots/sr03.webp" width="30%">
<img src="https://raw.githubusercontent.com/aayushsingla/comrades/master/Screenshots/sr04.webp" width="30%"> <img src="https://raw.githubusercontent.com/aayushsingla/comrades/master/Screenshots/sr05.webp" width="30%"> <img src="https://raw.githubusercontent.com/aayushsingla/comrades/master/Screenshots/sr06.webp" width="30%">
<img src="https://raw.githubusercontent.com/aayushsingla/comrades/master/Screenshots/sr07.webp" width="30%"> <img src="https://raw.githubusercontent.com/aayushsingla/comrades/master/Screenshots/sr08.jpeg" width="30%"> <img src="https://raw.githubusercontent.com/aayushsingla/comrades/master/Screenshots/sr09.jpeg" width="30%">

