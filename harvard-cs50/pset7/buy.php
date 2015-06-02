<?

    // require common code
    require_once("includes/common.php");

    $symbol = strtoupper($_GET["symbol"]);

    if ($symbol != "")
        $s = lookup($symbol);

?>

<!DOCTYPE html>

<html>

  <head>
    <link href="css/styles.css" rel="stylesheet" type="text/css">
    <title>C$50 Finance: Buying</title>
  </head>

  <body>

    <div id="top">
      <a href="index.php"><img alt="C$50 Finance" src="images/logo.gif"></a>
    </div>

    <div id="middle">
      <form action="buy2.php" method="post">
        <table>
          <tr>
            <td>Symbol:</td>
            <td><input name="symbol" type="text" value="<?= $symbol ?>"></td>
          </tr>
          <? if ($s): ?>
          <tr>
            <td>Description:</td>
            <td><?= $s->name ?></td>
          </tr>
          <tr>
            <td>Value:</td>
            <td><?= printf("%.2f", $s->price); ?></td>
          </tr>
          <? endif ?>
          <tr>
            <td>Shares:</td>
            <td><input name="shares" type="text" value="1"></td>
          </tr>
          <tr>
            <td colspan="2"><input type="submit" value="Buy"></td>
          </tr>
        </table>
      </form>
    </div>

    <div id="bottom">
      or go back to the <a href="index.php">main</a> page
    </div>

  </body>

</html>
