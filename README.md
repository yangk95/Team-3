# ICS499 - Team 3

## Table of Contents

- [Summary](#summary)
- [Getting Started](#getting-started)
  - [DB Setup](#db-setup)
- [Resource](#resource)
 
## Summary

Park Smart Application is a user-friendly application that allows drivers to view real-time parking availability, reserve spots, and make secure payments through the app. Whether you're a student, faculty member, visitor or even a parent, ParkSmart helps you save time by directing you to available spots quickly and reducing unnecessary circling.

### Envision

per contracts with application listing parking manager to have Parking Garage, Parking Street list all of the parking spots. Plan is to intergrate JSON of parking list to database using sqlite3. 

Upon having database fulfilled, application will perform requests of pulling data, post/insert new data. With admin right, contract might modified upon both parties such as garage under maintenances, closure, reopening, newly established. 

#### Manager: TBD
- View statistic of performance.
- Manage garage and parking spot.
- Manage account including payment.
- Report/view violation of contract. 
- Get Help guide (instruction on how to use application).

#### User
- Search nearby parking spots available
- Reserve and book parking spots
- Manage their account including payment method
- Report violation
- Get Help guide (instruction on how to use application)

## Getting Started

### App Setup
In resource -> database folder, this is where sql file will reside where all data Garage, Street, parking spot information reside. Currently no envision for User, Payment to be saved in the database (take PCI DSS into consideration). 

Run application at Init.Java. 

### DB Setup

#### 1️⃣ Install SQLite3
SQLite3 is required to manage the local database.

- **MacOS/Linux (via Homebrew):**  
  ```sh
  brew install sqlite
  ```
- **Windows**
1. Download the SQLite tools from https://www.sqlite.org/download.html
2. Under "Precompiled Binaries for Windows" package download `sqlite-tools-win-x64-3502000.zip`.
3. Unzip and place contents somewhere on your computer (e.g., C:\sqlite3\).
4. Add the SQLite binary location to your system PATH:
    1. Open Environment Variables
    2. Under System Variables, edit Path
    3. Click New and add the path to your SQLite folder
    4. Click OK to save the changes

## Resource

- Github: https://github.com/yangk95/Team-3
- Google Drive: https://drive.google.com/drive/folders/1Gz3VLfBDjzPn4VDCh9lHd91zIyhj5ShN?usp=drive_link 
- UML: ParkSmartUML.drawio (Google Drive: https://drive.google.com/file/d/1Pju5YHfA14OkfKeg7pD_uANTKoQE3h_h/view?usp=drive_link)
