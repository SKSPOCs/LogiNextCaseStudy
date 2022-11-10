# LogiNext Case Study by **Sachin Shelke**

####Artifact Info
|    Perticulars                               | Details                                                   |
    | ------------------------------------------ | -------------------------------------------------------- |
| Build Version                          | 1.0                                                         |
| Version Code                          | 1                                                           |
| Application Package Name      | com.loginext.casestudy                           |
| Min Android version                | Lollypop (API Level - 21 / Version 5.0)     |
| Target Android version           | Tiramisu (API Level - 33 / Version 13)      |
| Compiled SDK                        | 33                                                          |
| Build System                        | Gradle using Android Gradle Plugin            |




### Features & Overview
- Create a UI with different types of list view
- Single UI Screen with AutoScalling (Only Portait)
- Combination of Horizontal & Vertical Scrolling UI.
- Nested Scrolling.
- Scope for Custom Component Creation (Based on the solution implemented, custom component is not required.)
- Runtime Image downloading (Using Coil. Recommented by Google over picasso & glid)
- MVVM Design Pattern
- Simple Clean Architecture Used (Partially)
- OOPS Concept used to abstract business logic. (Refer BaseAdapter & BaseViewHolder)
- Maximum use of system libraries.



###Known
- Network error not displayed.
- Banner scrolling have issue with last tile.
- Search Functionality not implemented.
- Blank screen page with simple text.
- Most of CTA doesn't as functionality as it is not available in the case study problem statement.
- Unit Test cases not written.


### Solution Approach
- Almost all the elements are dynamic in nature.
- All the elements are driven through RecyclerView except Banners.
- Banner are driven through ViewPager2.
- Section after collection is again dynamic in nature using nested recycler view for horizontal scrolling and vertical scrolling
  - Recommended & Sales restaurants are dynamic
  - Even if there are few more category comes through API, api will adjust accordingly.
  - There is another RecyclerView in RestaurantCollection which is used for horizontal scrolling. ***(Hence Custom Component Not required.)***

  ### Permission Required
  - Full Internet Access.



## How to Build & Run the Project

## Prerequisite
- Android Studio Dolphin and Above [Click Here](https://developer.android.com/studio)
- Android SDK and Udates SDK images of Android Level 21 and 33 [Click here to know more](https://developer.android.com/studio/intro/update)
- Update Android Build Tool to 33  [Click here to know more](https://developer.android.com/studio/intro/update)


### Tools & Tech Stack Used
- Kotlin
- MVVM
- Clean Architecture (Partially)
- Android Studio
- Third Party Libraries - [Retrofit](https://square.github.io/retrofit/), [Coil](https://coil-kt.github.io/coil/) (Image Loading)


### Testing Environment
- Android Studio Dolphin | 2021.3.1 Patch 1
- Test Device - Pixel 2 XL (Android 11)

###Steps to Run the Project
Note:
- Please connect your phone to laptop or desktop
- Enable Developer Option (https://developer.android.com/studio/debug/dev-options)
- Enable USB for you to laptop or desktop

#### Option - 1 (Using Git URL)

- Open Android Studio
- File --> New --> Project From Version Control
  - Select Git
  - Enter URL (https://github.com/SKSPOCs/LogiNextCaseStudy/)
  - Select your desire directory to clone the project.
  - Open project in New Window
- Update your Android SDK path to local.properties file
- Build -> Make Project
- Run -> Run App. -> Select Target Device. -> Run

#### Option - 1 (Using Local Folder File)

- Open Android Studio
- File --> Open --> Select the Project Folder
- Update your Android SDK path to local.properties file
- Build -> Make Project
- Run -> Run App. -> Select Target Device. -> Run



###Thanks
Hope you find it easy and useful.
For any query / doubt / explaination, you can reach me out on (sachin.worldnet@gmail.com)
