<? 
    // require common code
    require_once("includes/common.php"); 

    // escape username and password for safety
    $uid = $_SESSION["uid"];
    $oldpassword = mysql_real_escape_string($_POST["oldpassword"]);
    $newpassword = mysql_real_escape_string($_POST["newpassword"]);
    $newpassword2 = mysql_real_escape_string($_POST["newpassword2"]);

    if ($oldpassword == "") apologize("You left Old Password blank.");
    if ($newpassword == "") apologize("You left New Password blank.");
    if ($newpassword2 == "") apologize("You left New Password Check blank.");
    if ($newpassword != $newpassword2) apologize("Passwords don't match.");

    // prepare SQL to check if user exists
    $sql = "SELECT uid FROM users WHERE uid='$uid' and password='$oldpassword'";

    // retrieve uid from user
    $result = mysql_query($sql);

    if (mysql_num_rows($result) != 1)
    {
        apologize("Username and Password don't match.");
    }

    // prepare SQL to insert user into table
    $sql = "UPDATE users SET password = '$newpassword' WHERE uid='$uid' and password='$oldpassword'";

	// insert row into table
    $result = mysql_query($sql);

	//if row not inserted
    if ($result == NULL)
    {
        apologize("Error changing password.");
    }
    else
    {
        // redirect to portfolio
        redirect("index.php");
    }       

?>
