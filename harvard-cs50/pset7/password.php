<?

    // require common code
    require_once("includes/common.php");
    
    //SQL to get user data
    $sql = "SELECT * FROM users WHERE uid='{$_SESSION["uid"]}'";

    // get results
    $result = mysql_query($sql);

    // if we found a row, remember user and redirect to portfolio
    if (mysql_num_rows($result) == 1)
    {
        // grab row
        $row = mysql_fetch_array($result);
        $username = $row["username"];
    }
    else
    {
        apologize("Unable to get user information.");
    }
    
?>

<!DOCTYPE html>

<html>

  <head>
    <link href="css/styles.css" rel="stylesheet" type="text/css">
    <title>C$50 Finance: Change Password</title>
  </head>

  <body>

    <div id="top">
      <a href="index.php"><img alt="C$50 Finance" src="images/logo.gif"></a>
    </div>

    <div id="middle">
      <form action="password2.php" method="post">
        <table>
          <tr>
            <td>Username:</td>
            <td><?= $username ?></td>
          </tr>
          <tr>
            <td>Old Password:</td>
            <td><input name="oldpassword" type="password"></td>
          </tr>
          <tr>
            <td>New Password:</td>
            <td><input name="newpassword" type="password"></td>
          </tr>
          <tr>
            <td>New Password Check:</td>
            <td><input name="newpassword2" type="password"></td>
          </tr>
          <tr>
            <td colspan="2"><input type="submit" value="Change Password"></td>
          </tr>
        </table>
      </form>
    </div>

    <div id="bottom">
      or go back to the <a href="index.php">main</a> page
    </div>

  </body>

</html>
