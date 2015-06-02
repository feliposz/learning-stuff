<?

    // require common code
    require_once("includes/common.php"); 

    $uid = $_SESSION["uid"];

?>

<!DOCTYPE html>

<html>

  <head>
    <link href="css/styles.css" rel="stylesheet" type="text/css">
    <title>C$50 Finance: Home</title>
  </head>

  <body>

    <div id="top">
      <a href="index.php"><img alt="C$50 Finance" height="110" src="images/logo.gif" width="544"></a>
    </div>

    <div id="middle">
        <?
        // SQL to get user portfolio listing
        $sql = "SELECT * FROM history WHERE uid='$uid'";

        // get results
        $result = mysql_query($sql);

        // error getting results for user
        if (!$result)
        {
            apologize("Unable to get history information.");
        }
        ?>
        
        <? if (mysql_num_rows($result) > 0): ?>

        <table class="listing">
            <caption>Your current portfolio</caption>
            <thead>
                <tr>
                    <td>Symbol</td>
                    <td>Shares</td>
                    <td>Value</td>
                    <td>Operation</td>
                    <td>Date/Time</td>
                </tr>
            </thead>
            <tbody>
            <? while ($row = mysql_fetch_array($result)): ?>
                <tr>
                    <td><?= $row["symbol"] ?></td>
                    <td><?= $row["shares"] ?></td>
                    <td><?= $row["value"] ?></td>
                    <td><?
                    if ($row["operation"] == "B")
                        echo "Buy";
                    elseif ($row["operation"] == "S")
                        echo "Sell";
                    else
                        echo "???";
                    ?></td>
                    <td><?= $row["datetime"] ?></td>
                </tr>
            <? endwhile ?>
            </tbody>
        </table>

        <? else: ?>

        <p>No history.</p>

        <? endif ?>

    </div>

    <div id="bottom">
      Go back to <a href="index.php">main page</a>.
    </div>

  </body>

</html>
