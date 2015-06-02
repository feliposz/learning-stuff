<?

    // require common code
    require_once("includes/common.php"); 

    // SQL to get user data
    $sql = "SELECT * FROM users WHERE uid='{$_SESSION["uid"]}'";

    // get results
    $result = mysql_query($sql);

    // if we found a row, remember user and redirect to portfolio
    if (mysql_num_rows($result) == 1)
    {
        // grab row
        $row = mysql_fetch_array($result);
        $username = $row["username"];
        $usercash = $row["cash"];
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
    <title>C$50 Finance: Home</title>
  </head>

  <body>

    <div id="top">
      <a href="index.php"><img alt="C$50 Finance" height="110" src="images/logo.gif" width="544"></a>
    </div>

    <div id="middle">
        Welcome, <?= $username ?>.<br>
        <p>Current cash: $ <? printf("%.2f", $usercash); ?></p>
        <p><a href="quote.php">Get quotes</a></p>
        <p><a href="buy.php">Buy stocks</a></p>
        <p><a href="history.php">Check history</a></p>
        <p><a href="password.php">Change password</a></p>

        <?
        // SQL to get user portfolio listing
        $sql = "SELECT * FROM portfolio WHERE uid='{$_SESSION["uid"]}'";

        // get results
        $result = mysql_query($sql);

        // error getting results for user
        if (!$result)
        {
            apologize("Unable to get portfolio information.");
        }
        ?>
        
        <? if (mysql_num_rows($result) > 0): ?>

        <table class="listing">
            <caption>Your current portfolio</caption>
            <thead>
                <tr>
                    <td>Symbol</td>
                    <td>Description</td>
                    <td>Shares</td>
                    <td>Value</td>
                    <td>Operation</td>
                </tr>
            </thead>
            <tbody>

            <? while ($row = mysql_fetch_array($result)): ?>

                <? $s = lookup($row["symbol"]); ?>
                <tr>
                    <td><?= $row["symbol"] ?></td>
                    <td><?= $s->name ?></td>
                    <td><?= $row["shares"] ?></td>
                    <td><? printf("%.2f", $s->price); ?></td>
                    <td>
                        <a href="sell.php?symbol=<?= urlencode($row["symbol"]) ?>">Sell</a>
                        <a href="buy.php?symbol=<?= urlencode($row["symbol"]) ?>">Buy</a>
                    </td>
                </tr>

            <? endwhile ?>

            </tbody>
        </table>

        <? else: ?>

        <p>No portfolio.</p>

        <? endif ?>

    </div>

    <div id="bottom">
      <a href="logout.php">log out</a>
    </div>

  </body>

</html>
