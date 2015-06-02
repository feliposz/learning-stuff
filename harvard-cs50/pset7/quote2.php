<?

    // require common code
    require_once("includes/common.php"); 

    // escape characters
    $symbol = strtoupper(urlencode($_POST["symbol"]));

    // check if symbol was specified
    if ($symbol == "")
        apologize("You left Symbol blank.");

    // lookup quote and create a Stock object
    $s = lookup($symbol);

    if ($s == NULL)
        apologize("Could not get quote for $symbol.");
?>

<!DOCTYPE html>

<html>

  <head>
    <link href="css/styles.css" rel="stylesheet" type="text/css">
    <title>C$50 Finance: Quote Result</title>
  </head>

  <body>

    <div id="top">
      <a href="index.php"><img alt="C$50 Finance" src="images/logo.gif"></a>
    </div>

    <div id="middle">
        A share of <?= $s->name ?> currently costs <?= $s->price ?>.<br>
        Want to <a href="buy.php?symbol=<?= $symbol ?>">buy</a> some shares?
    </div>

    <div id="bottom">
      go back to the <a href="index.php">main</a> page<br>
      or get another <a href="quote.php">quote</a>
    </div>

  </body>

</html>
