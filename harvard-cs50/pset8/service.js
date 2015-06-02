/****************************************************************************
 * service.js
 *
 * Computer Science 50
 * Problem Set 8
 *
 * Implements a shuttle service.
 ***************************************************************************/


// default height
var HEIGHT = 0.8;

// default latitude
var LATITUDE = 42.3745615030193;

// default longitude
var LONGITUDE = -71.11803936751632;

// default heading
var HEADING = 1.757197490907891;

// default number of seats
var SEATS = 35;

// default velocity
var VELOCITY = 50;

// FELIPO: default full tank value
var FULLGASTANK = 1000;

// FELIPO: user score
var score = 0;

// FELIPO: seconds since game started
var gametime = 0;

// FELIPO: check for secret code
var konami = "";

// FELIPO: enable/disable fly mode if user enters secret code
var flymode = false;

// FELIPO: global reference to timer
var timerInterval = null;

// FELIPO: XML HTTP Response object for AJAX
var xhr = null;

// FELIPO: autopilot state
var autostate = false;

// global reference to shuttle's marker on 2D map
var bus = null;

// global reference to 3D Earth
var earth = null;

// global reference to 2D map
var map = null;

// global reference to shuttle
var shuttle = null;


// load version 1 of the Google Earth API
google.load("earth", "1");

// load version 3 of the Google Maps API
google.load("maps", "3", {other_params: "sensor=false"});

/*
 * void
 * failureCB(errorCode)
 *
 * Called if Google Earth fails to load.
 */

function failureCB(errorCode) 
{
    // report error unless plugin simply isn't installed
    if (errorCode != ERR_CREATE_PLUGIN)
    {
        alert(errorCode);
    }
}


/*
 * void
 * frameend()
 *
 * Handler for Earth's frameend event.
 */

function frameend() 
{
    shuttle.update();
}


/*
 * void
 * initCB()
 *
 * Called once Google Earth has loaded.
 */

function initCB(instance) 
{
    // retain reference to GEPlugin instance
    earth = instance;

    // specify the speed at which the camera moves
    earth.getOptions().setFlyToSpeed(100);

    // show buildings
    earth.getLayerRoot().enableLayerById(earth.LAYER_BUILDINGS, true);

    // prevent mouse navigation in the plugin
    earth.getOptions().setMouseNavigationEnabled(false);

    // instantiate shuttle
    shuttle = new Shuttle({
     heading: HEADING,
     height: HEIGHT,
     latitude: LATITUDE,
     longitude: LONGITUDE,
     planet: earth,
     velocity: VELOCITY,
     fuel: FULLGASTANK
    });

    // synchronize camera with Earth
    google.earth.addEventListener(earth, "frameend", frameend);

    // synchronize map with Earth
    google.earth.addEventListener(earth.getView(), "viewchange", viewchange);

    // update shuttle's camera
    shuttle.updateCamera();

    // show Earth
    earth.getWindow().setVisibility(true);


    // populate Earth with passengers and houses
    populate();
    
    // FELIPO: rotate bus initially
    rotateBus();
    
    // FELIPO: load dynamic interface items
    startinterface();
}

/*
 * boolean
 * keystroke(event, state)
 *
 * Handles keystrokes.
 * FELIPO: Handle secretcode keystrokes too.
 */

function keystroke(event, state)
{
    // ensure we have event
    if (!event)
    {
        event = window.event;
    }
    
    // left arrow
    if (event.keyCode == 37)
    {
        if (!state) secretcode("L");
        shuttle.states.turningLeftward = state;
        if (state) rotateBus();
        return false;
    }

    // up arrow
    else if (event.keyCode == 38)
    {
        if (!state) secretcode("U");
        shuttle.states.tiltingUpward = state;
        return false;
    }

    // right arrow
    else if (event.keyCode == 39)
    {
        if (!state) secretcode("R");
        shuttle.states.turningRightward = state;
        if (state) rotateBus();
        return false;
    }

    // down arrow
    else if (event.keyCode == 40)
    {
        if (!state) secretcode("D");
        shuttle.states.tiltingDownward = state;
        return false;
    }

    // A, a
    else if (event.keyCode == 65 || event.keyCode == 97)
    {
        if (!state) secretcode("L");
        shuttle.states.slidingLeftward = state;
        return false;
    }

    // D, d
    else if (event.keyCode == 68 || event.keyCode == 100)
    {
        if (!state) secretcode("R");
        shuttle.states.slidingRightward = state;
        return false;
    }
  
    // S, s
    else if (event.keyCode == 83 || event.keyCode == 115)
    {
        if (!state) secretcode("D");
        shuttle.states.movingBackward = state;     
        return false;
    }

    // W, w
    else if (event.keyCode == 87 || event.keyCode == 119)
    {
        if (!state) secretcode("U");
        shuttle.states.movingForward = state;    
        return false;
    }
  
    // Q, q
    else if (event.keyCode == 81 || event.keyCode == 113)
    {
        if (flymode && state) {
            shuttle.cameraAltitude += 1;
            if (shuttle.cameraAltitude > 150) {
                shuttle.cameraAltitude = 150;
            }
        }        
        return false;
    }

    // E, e
    else if (event.keyCode == 69 || event.keyCode == 101)
    {
        if (flymode && state) {
            shuttle.cameraAltitude -= 1;
            if (shuttle.cameraAltitude < shuttle.height) {
                shuttle.cameraAltitude = shuttle.height;
            }
        }        
        return false;
    }

    // -
    else if (event.keyCode == 189 && state)
    {
        shuttle.velocity -= 5;
        if (shuttle.velocity < 5) {
            shuttle.velocity = 5;
        }
        return false;
    }

    // +, =
    else if (event.keyCode == 187 && state)
    {
        shuttle.velocity += 5;
        if (shuttle.velocity > 150) {
            shuttle.velocity = 150;
        }
        return false;
    }

    // 0 
    else if (event.keyCode == 48 && state)
    {
        shuttle.velocity = VELOCITY;
        return false;
    }

    return true;
}


/*
 * void
 * load()
 *
 * Loads application.
 */

function load()
{
    // embed 2D map in DOM
    var latlng = new google.maps.LatLng(LATITUDE, LONGITUDE);
    map = new google.maps.Map(document.getElementById("map"), {
     center: latlng,
     disableDefaultUI: true,
     mapTypeId: google.maps.MapTypeId.ROADMAP,
     navigationControl: true,
     scrollwheel: false,
     zoom: 17
    });

    // prepare shuttle's icon for map
    bus = new google.maps.Marker({
     icon: "bus.png", //"http://maps.gstatic.com/intl/en_us/mapfiles/ms/micons/bus.png"
     map: map,
     title: "you are here"
    });
    
    // embed 3D Earth in DOM
    google.earth.createInstance("earth", initCB, failureCB);
    
}

/*
 * void
 * populate()
 *
 * Populates Earth with passengers and houses.
 */

function populate()
{
    // mark houses
    for (var house in HOUSES)
    {
        // plant house on map
        new google.maps.Marker({
         icon: "http://google-maps-icons.googlecode.com/files/home.png",
         map: map,
         position: new google.maps.LatLng(HOUSES[house].lat, HOUSES[house].lng),
         title: house
        });
    }

    // place markers for gas stations
    for (var station in STATIONS)
    {
        // prepare placemark
        var placemark = earth.createPlacemark("");
        placemark.setName(STATIONS[station].name);

        // prepare icon
        var icon = earth.createIcon("");
        icon.setHref("http://google-maps-icons.googlecode.com/files/gazstation.png");

        // prepare style
        var style = earth.createStyle("");
        style.getIconStyle().setIcon(icon);
        style.getIconStyle().setScale(3.0);

        // prepare stylemap
        var styleMap = earth.createStyleMap("");
        styleMap.setNormalStyle(style);
        styleMap.setHighlightStyle(style);

        // associate stylemap with placemark
        placemark.setStyleSelector(styleMap);

        // prepare point
        var point = earth.createPoint("");
        point.setAltitudeMode(earth.ALTITUDE_RELATIVE_TO_GROUND);
        point.setLatitude(STATIONS[station].lat);
        point.setLongitude(STATIONS[station].lng);
        point.setAltitude(1.5);
	
        // associate placemark with point
        placemark.setGeometry(point);

        // add placemark to Earth
        earth.getFeatures().appendChild(placemark);

        // plant gas stations on map
        var marker = new google.maps.Marker({
         icon: "http://google-maps-icons.googlecode.com/files/gazstation.png",
         map: map,
         position: new google.maps.LatLng(STATIONS[station].lat, STATIONS[station].lng),
         title: STATIONS[station].name
        });
        
    }
    
    // get current URL, sans any filename
    //var url = window.location.href.substring(0, (window.location.href.lastIndexOf("/")) + 1);
    
    //FELIPO: trying to use the courses original page, since loading them locally is not working
    //FELIPO: i guess google only tries to load images directly... need to check api doc
    var url = "http://cdn.cs50.net/2010/fall/lectures/10/src";

    // scatter passengers
    for (var i = 0; i < PASSENGERS.length; i++)
    {
        // pick a random building
        var building = BUILDINGS[Math.floor(Math.random() * BUILDINGS.length)];

        // prepare placemark
        var placemark = earth.createPlacemark("");
        placemark.setName(PASSENGERS[i].name + " to " + PASSENGERS[i].house);

        // prepare icon
        var icon = earth.createIcon("");
        icon.setHref(url + "/passengers/" + PASSENGERS[i].username + ".jpg");

        // prepare style
        var style = earth.createStyle("");
        style.getIconStyle().setIcon(icon);
        style.getIconStyle().setScale(3.0);

        // prepare stylemap
        var styleMap = earth.createStyleMap("");
        styleMap.setNormalStyle(style);
        styleMap.setHighlightStyle(style);

        // associate stylemap with placemark
        placemark.setStyleSelector(styleMap);

        // prepare point
        var point = earth.createPoint("");
        point.setAltitudeMode(earth.ALTITUDE_RELATIVE_TO_GROUND);
        point.setLatitude(building.lat);
        point.setLongitude(building.lng);
        point.setAltitude(1.5);
	
        // associate placemark with point
        placemark.setGeometry(point);

        // add placemark to Earth
        earth.getFeatures().appendChild(placemark);

        // add marker to map
        var marker = new google.maps.Marker({
         icon: "http://maps.gstatic.com/intl/en_us/mapfiles/ms/micons/man.png",
         map: map,
         position: new google.maps.LatLng(building.lat, building.lng),
         title: PASSENGERS[i].name + " at " + building.name
        });

        // FELIPO: store information in object PASSENGERS[i] for checking and removal on pickup
        PASSENGERS[i].lat = building.lat;
        PASSENGERS[i].lng = building.lng;
        PASSENGERS[i].earthPlacemark = placemark;
        PASSENGERS[i].mapsPlacemark = marker;
        
        // initialize some relevant info
        PASSENGERS[i].picked = false;
    }
}


/*
 * void
 * viewchange()
 *
 * Handler for Earth's viewchange event.
 */

function viewchange() 
{
    // keep map centered on shuttle's marker
    var latlng = new google.maps.LatLng(shuttle.position.latitude, shuttle.position.longitude);
    map.setCenter(latlng);
    bus.setPosition(latlng);
}


/*
 * void
 * unload()
 *
 * Unloads Earth.
 */

function unload()
{
    google.earth.removeEventListener(earth.getView(), "viewchange", viewchange);
    google.earth.removeEventListener(earth, "frameend", frameend);
    // FELIPO: clear interval for timer
    clearInterval(timerInterval);
}


/*
 * void
 * pickup()
 *
 * Picks up nearby passengers.
 * FELIPO: Implemented by me
 */

function pickup()
{

    var picked = false;

    document.getElementById("announcements").innerText = "Picked: ";
    
    for (var i = 0; i < PASSENGERS.length; i++)
    {
        // skip passengers already picked up
        if (PASSENGERS[i].picked === true) {
            continue;
        }
        
        // check if close enough to passenger
        var dist = shuttle.distance(PASSENGERS[i].lat, PASSENGERS[i].lng);
        if (dist <= 15) {
            // check for available seats
            var seatsList = document.getElementById("seats").getElementsByTagName("li");
            var available = false;
            for (var j = 0; j < seatsList.length; j++) {
                if (seatsList[j].innerText === "Empty Seat") {                
                    // place passenger on first available seat
                    seatsList[j].innerText = PASSENGERS[i].name + " to " + PASSENGERS[i].house;

                    // remove placemark from google earth plugin
                    earth.getFeatures().removeChild(PASSENGERS[i].earthPlacemark);
                    
                    // remove marker from google maps
                    PASSENGERS[i].mapsPlacemark.setMap(null);
                    
                    // set some control
                    available = true;                    
                    PASSENGERS[i].picked = true;
                    picked = true;

                    // append passenger name to picked list
                    document.getElementById("announcements").innerText += PASSENGERS[i].name + " ";
                    
                    updateScore(5);
                    
                    // found a seat, exit the for loop
                    break;
                }
            }
            
            // no available seat
            if (!available) {
                if (picked) {
                    document.getElementById("announcements").innerText += "<br>No more available seats, sorry.";
                } else {
                    document.getElementById("announcements").innerText = "No available seats, sorry.";
                }
                return;
            }    
        }
    }
    
    // no one has been picked
    if (!picked) {
        document.getElementById("announcements").innerText = "No one to pick here.";
    }
}

/*
 * void
 * dropoff()
 *
 * Drops up passengers if their stop is nearby.
 * FELIPO: Implemented by me.
 */

function dropoff()
{
    // FELIPO: All implemented by me! :)
    
    var dropped = false;
   
    document.getElementById("announcements").innerText = "Dropped: ";
    
    var seatsList = document.getElementById("seats").getElementsByTagName("li");
    // NOTE: element 0 is the OL tag, skip it!
    for (var j = 0; j < seatsList.length; j++) {
        // skip empty seats
        if (seatsList[j].innerText !== "Empty Seat") {
            // get name of the house and passenger
            var s = seatsList[j].innerText;
            var passengerName = s.substring(0, s.lastIndexOf(" to "));
            var houseName = s.substring(s.lastIndexOf(" to ") + 4, s.length);
            
            // calculate distance of shuttle from house
            var dist = shuttle.distance(HOUSES[houseName].lat, HOUSES[houseName].lng);
            
            // drop passenger, make seat empty
            if (dist <= 35) {
                document.getElementById("announcements").innerText += passengerName + " ";
                seatsList[j].innerText = "Empty Seat";
                dropped = true;
                updateScore(100);
            }
        }
    }
    
    if (!dropped) {
        document.getElementById("announcements").innerText = "No one to drop here.";
    }
}

/*
 * FELIPO:
 * void
 * score(int score)
 *
 * Increase user score and update display
 */
function updateScore(value)
{
    score += value;
    
    document.getElementById("score").innerText = "Score: " + score;
}

/*
 * FELIPO:
 * void
 * timerCB()
 *
 * A simple timer callback that updates a timer display
 */
function timerCB()
{
    //increment gametime by 1 second (interval of call is 1000 miliseconds)
    gametime++;

    //calculate seconds, minutes and hours
    var timer = gametime;
    var s=timer % 60;
    timer = Math.floor(timer / 60);
    var m=timer % 60;
    timer = Math.floor(timer / 60);
    var h=timer;
    
    //force 2 digits
    if (h < 10) h = '0' + h;
    if (m < 10) m = '0' + m;
    if (s < 10) s = '0' + s;
    
    var str = "Game time: " + h + ":" + m + ":" + s;
    str += "\nFuel: " + Math.round(shuttle.fuel);
    
    document.getElementById("timer").innerText = str;
}

/*
 * FELIPO:
 * void
 * sortSeatsByHouse()
 *
 * Sort list of passenger seats by their destination house (using BubbleSort)
 */
function sortSeatsByHouse()
{
    // get list of passengers on the shuttle
    var seatsList = document.getElementById("seats").getElementsByTagName("li");
    var s, s1, s2;
    
    do {
        var swapped = false;
        for (var j = 0; j < seatsList.length - 1; j++) {
            // get house name for both elements
            s = seatsList[j].innerText;
            s1 = s.substring(s.lastIndexOf(" to ") + 4, s.length);
            s = seatsList[j+1].innerText;
            s2 = s.substring(s.lastIndexOf(" to ") + 4, s.length);
            // if out of order, swap (also, move Empty Seat to end of the list)
            if (s1 > s2 || (seatsList[j].innerText === "Empty Seat" && seatsList[j+1].innerText !== "Empty Seat")) { 
                var tmp = seatsList[j].innerText;
                seatsList[j].innerText = seatsList[j+1].innerText;
                seatsList[j+1].innerText = tmp;
                swapped = true;
            }
        }
    } while (swapped === true);
}

/*
 * FELIPO:
 * void
 * secretcode(dir)
 *
 * Check if user entered secret code.
 */
function secretcode(dir)
{
    if (konami.length > 7) {
        konami = konami.substring(1, 8);
    }

    konami += dir;
    
    if (konami === "UUDDLRLR") {
        flymode = !flymode;
        
        hyperjump();
        konami = "";
    }
    if (konami === "LLRRLLRR") {
        shuttle.fuel = FULLGASTANK;
    }
}

/* FELIPO:
 * void
 * hyperjump()
 *
 * execute a jump upwards when flymode is activated.
 * or execute a fall, when it's disabled
 */

function hyperjump()
{
    if (flymode) {
        shuttle.cameraAltitude += 1;
        if (shuttle.cameraAltitude > 150) {
            return;
        }        
    } else {
        shuttle.cameraAltitude -= 1;
        if (shuttle.cameraAltitude < shuttle.height) {
            shuttle.cameraAltitude = shuttle.height;
            return;
        }
    }
    window.setTimeout(hyperjump, 50);
}



/* FELIPO:
 * void
 * startinterface()
 *
 * load dynamic interface items
 */
function startinterface() { 
     // FELIPO: set interval for displaying a timer for the user
    timerInterval = window.setInterval(timerCB, 1000);
    
    // fill the dropdown list with destinations
    var dest = document.getElementById("destination");
    for (var name in HOUSES) {
        
        var option = document.createElement("option");
        option.text = name;
        try {
            // for IE earlier than version 8
            dest.add(option, dest.options[null]);
        } catch (e) {
            dest.add(option, null);
        }
    }
}

/* FELIPO:
 * void
 * teleport()
 *
 * Teleport shuttle to selected destination
 */
function teleport() {
    dest = document.getElementById("destination");
    name = dest.options[dest.selectedIndex].value;
    if (name === "none") {
        return;
    } else {
        travel(HOUSES[name].lat, HOUSES[name].lng);
    }
}

/* FELIPO:
 * void
 * travel(lat, lng)
 *
 * Teleport shuttle to coordinates
 */
function travel(lat, lng) {
    shuttle.position.latitude = lat;
    shuttle.position.longitude = lng;
    shuttle.localAnchorCartesian = 
        V3.latLonAltToCartesian([shuttle.position.latitude, shuttle.position.longitude, shuttle.position.altitude]);
}

/* FELIPO:
 * void
 * whereami()
 *
 * Get geographical information for current position
 */
function whereami()
{
    // Google API to get information about location
    // http://code.google.com/intl/pt-BR/apis/maps/documentation/geocoding/
    var url = 'http://maps.google.com/maps/api/geocode/json?latlng='
            + shuttle.position.latitude + ',' + shuttle.position.longitude + '&sensor=false';
    
    document.getElementById("announcements").innerText = "Getting information for location...";
    
    // initialize xhr only once
    if (xhr === null) {
        if (window.XMLHttpRequest) {// code for IE7+, Firefox, Chrome, Opera, Safari
            xhr = new XMLHttpRequest();
        } else {// code for IE6, IE5
            xhr = new ActiveXObject("Microsoft.XMLHTTP");
        }
    }
    // set callback for AJAX
    xhr.onreadystatechange = whereamiCB; 
    
    // make call to google api
    xhr.open("GET", url, true);    
    xhr.send();
}

/* FELIPO:
 * void
 * whereami
 *
 * Callback function for AJAX response
 */
function whereamiCB()
{
    if (xhr.readyState === 4) {
        if (xhr.status === 200) {
            // API response is in JSON format, evaluate it
            response = eval('(' + xhr.responseText + ')');
            if (response.status === "OK") {
                document.getElementById("announcements").innerText = response.results[0].formatted_address;
            } else {
                document.getElementById("announcements").innerText = "Error: " + response.status;
            }
        }
    }
}

/*
 * void
 * autopilot()
 *
 * Start autopilot
 */
function autopilot()
{
    autostate = !autostate; // toggle autopilot
   
    if (autostate) {
        // determine coordinates of destination
        dest = document.getElementById("destination");
        name = dest.options[dest.selectedIndex].value;
        if (name === "none") {
            return;
        }
        autopilotCB(HOUSES[name].lat, HOUSES[name].lng);
    }
}

/*
 * void
 * autopilotCB(lat, lng)
 *
 * Helper function for autopilot that is called every 100 miliseconds to move shuttle
 */
 
function autopilotCB(lat, lng)
{
    // if autopilot toggled off while executing, abort
    if (autostate) {
    
        // make shuttle face destination
        // need to recalculate everytime, perhaps due to small imprecisions on float ?!?!?
        // also allow user to strafe and move backwards while autopilot is engaged without loosing destination
        dlng = shuttle.position.longitude - lng;
        dlat = shuttle.position.latitude - lat;
        shuttle.headingAngle = Math.atan(dlng/dlat);
        if (dlat > 0) {
            shuttle.headingAngle += Math.PI;
        }
        rotateBus();
        
        // check distance from destination
        dist = shuttle.distance(lat, lng);
        if (dist < 15) {
            // if reached, disingage autopilot
            autostate = false;
            shuttle.states.movingForward = false;
            return;
        } else {
            // move shuttle (simulate forward key pressed)
            shuttle.states.movingForward = true; 
            
            // repeat until target is reached
            window.setTimeout(function() { autopilotCB(lat, lng); }, 100);
        }
    } else {
        // simulate forward unpressed. do not call setTimeout to stop cycle...
        shuttle.states.movingForward = false;
    }
}

/*
 * void
 * rotateBus()
 *
 * Change marker in map for the rotated version
 */
function rotateBus()
{
    // calculate angle for shuttle heading
    var angle = Math.floor((shuttle.headingAngle * 180 / Math.PI) + 720) % 360;
    // sprite matrix is 6x6 with each sprite rotating 10 degrees starting with 0 (pointing up)
    x = Math.floor(angle / 10) % 6;
    y = Math.floor(Math.floor(angle / 10) / 6);
    bus.setIcon(new google.maps.MarkerImage(
        "bus_rot.png", // sprite matrix 6x6
        new google.maps.Size(40, 40), // size of sprite
        new google.maps.Point(40*x, 40*y), // position of rotated image
        new google.maps.Point(20, 20), // origin
        null
    ));
}
