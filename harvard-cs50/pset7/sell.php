<?

    // require common code
    require_once("includes/common.php");

    $symbol = mysql_real_escape_string(strtoupper($_GET["symbol"]));

    // check if symbol was specified
    if ($symbol == "")
        apologize("Invalid symbol.");

    // SQL to get portfolio data
    $sql = "SELECT * FROM portfolio WHERE uid='{$_SESSION["uid"]}' and symbol='$symbol'";

    // get results
    $result = mysql_query($sql);

    // data found
    if (mysql_num_rows($result) == 1)
    {
        // grab row
        $row = mysql_fetch_array($result);
        $s = lookup($row["symbol"]);
    }
    else
    {
        apologize("Unable to get information for this share.");
    }

?>

<!DOCTYPE html>

<html>

  <head>
    <link href="css/styles.css" rel="stylesheet" type="text/css">
    <title>C$50 Finance: Selling</title>
  </head>

  <body>

    <div id="top">
      <a href="index.php"><img alt="C$50 Finance" src="images/logo.gif"></a>
    </div>

    <div id="middle">
      <form action="sell2.php" method="post">
        <table>
          <tr>
            <td>Symbol:</td>
            <td><?= $row["symbol"] ?><input name="symbol" type="hidden" value="<?= $row["symbol"] ?>"></td>
          </tr>
          <tr>
            <td>Description:</td>
            <td><?= $s->name ?></td>
          </tr>
          <tr>
            <td>Shares:</td>
            <td><input name="shares" type="text" value="<?= $row["shares"] ?>"></td>
          </tr>
          <tr>
            <td>Value:</td>
            <td><?= printf("%.2f", $s->price); ?></td>
          </tr>
          <tr>
            <td colspan="2"><input type="submit" value="Sell"></td>
          </tr>
        </table>
      </form>
    </div>

    <div id="bottom">
      or go back to the <a href="index.php">main</a> page
    </div>

  </body>

</html>
