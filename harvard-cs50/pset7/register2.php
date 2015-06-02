<?

    // require common code
    require_once("includes/common.php"); 

    // escape username and password for safety
    $username = mysql_real_escape_string($_POST["username"]);
    $password = mysql_real_escape_string($_POST["password"]);
    $password2 = mysql_real_escape_string($_POST["password2"]);

    if ($username == "") apologize("You left Username blank.");
    if ($password == "") apologize("You left Password blank.");
    if ($password2 == "") apologize("You left Password Check blank.");
    if ($password != $password2) apologize("Passwords don't match.");

    // prepare SQL to check if user exists
    $sql = "SELECT uid FROM users WHERE username='$username'";

    // retrieve uid from user
    $result = mysql_query($sql);

    if (mysql_num_rows($result) == 1)
    {
        apologize("User already exists, try a different Username.");
    }

    // prepare SQL to insert user into table
    $sql = "INSERT INTO users (username, password, cash) VALUES ('$username', '$password', 10000.0000)";

    // insert row into table
    $result = mysql_query($sql);

    // if row not inserted
    if ($result == NULL)
    {
        apologize("Error registering user.");
    }
    else
    {
        // login the user after insertion with id generated in insert above
        $_SESSION["uid"] = mysql_insert_id();

        // redirect to portfolio
        redirect("index.php");
    }       

?>
