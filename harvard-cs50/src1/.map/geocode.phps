<?

    // www.cs50.net's Google API key
    define("KEY", "ABQIAAAA8igYd929VTmOEMLNjNyP1xSMPh09-euIyL03Rq61b9cUA87DYRQwRKaWMQ8Z-hHN1K5Bqvq7Mjw3iA");

    // get list of hometowns
    $hometowns = file("survey.csv");

    // prepare URL
    $url = "http://maps.google.com/maps/geo?output=xml&key=" . KEY;

    // start XML
    header("Content-type: text/xml");
    print "<kml xmlns='http://earth.google.com/kml/2.0'>";
    print "<Folder>";
    print "<name>This is CS 50</name>";

    // iterate over hometowns
    foreach ($hometowns as $hometown)
    {
        // append hometown to URL
        $hometown = trim($hometown);
        if ($hometown == "")
            continue;
        $request = $url . "&q=" . urlencode($hometown);

        // get URL
        $contents = file_get_contents($request);

        // avoid apparent bug in Google's UTF-8 encodings
        $contents = utf8_encode($contents);

        // parse Google's XML
        $xml = simplexml_load_string($contents);

        // check status code
        $status = $xml->Response->Status->code;
        if ($xml->Response->Status->code == "200")
        {
            // get hometown's coordinates
            $coordinates = $xml->Response->Placemark->Point->coordinates;
            $latlng = split(",", $coordinates);
            $lat = $latlng [1];
            $lng = $latlng[0];

            // output Placemark
            print "<Placemark>\n";
            print "<name>$hometown</name>\n";
            print "<Point><coordinates>$lng,$lat,0</coordinates></Point>\n";
            print "</Placemark>\n";
        }

        // don't get blacklisted
        sleep(2);
    }

    // finish XML
    print "</Folder>";
    print "</kml>";
?>
