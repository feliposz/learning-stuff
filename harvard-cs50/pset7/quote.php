<?

    // require common code
    require_once("includes/common.php");

?>

<!DOCTYPE html>

<html>

  <head>
    <link href="css/styles.css" rel="stylesheet" type="text/css">
    <title>C$50 Finance: Quote</title>
  </head>

  <body>

    <div id="top">
      <a href="index.php"><img alt="C$50 Finance" src="images/logo.gif"></a>
    </div>

    <div id="middle">
      <form action="quote2.php" method="post">
        <table>
          <tr>
            <td>Symbol:</td>
            <td><input name="symbol" type="text"></td>
          </tr>
          <tr>
            <td colspan="2"><input type="submit" value="Get Quote"></td>
          </tr>
        </table>
      </form>
    </div>

    <div id="bottom">
      or go back to the <a href="index.php">main</a> page
    </div>

  </body>

</html>
