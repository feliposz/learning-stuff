<?

    // require common code
    require_once("includes/common.php"); 

    $symbol = strtoupper($_POST["symbol"]);
    $uid = $_SESSION["uid"];

    // check if symbol was specified
    if ($symbol == "")
        apologize("Invalid symbol.");

    if (!preg_match("/^\d+$/", $_POST["shares"]))
        apologize("Invalid number of shares to buy.");
    else
        $shares = intval($_POST["shares"]);

    if ($shares <= 0)
        apologize("Can't buy a negative ammount of shares.");

    // get current info for company's share
    $s = lookup($symbol);

    if (!$s)
        apologize("Can't find info for this share.");

    // calculate amount of money to pay for shares
    $cash = $shares * $s->price;

    // check if user has enough money
    $sql = "SELECT cash FROM users WHERE uid='$uid'";
    $result = mysql_query($sql);
    if (mysql_num_rows($result) == 1)
    {
        $row = mysql_fetch_array($result);
        if ($row["cash"] > $cash)
        {
            $sql = "UPDATE users SET cash = cash - $cash WHERE uid='$uid'";
            $result = mysql_query($sql);
            if (!$result)
            {
                apologize("Couldn't update cash for user.");
            }
        }
        else
        {
            apologize("Sorry, not enough cash.");
        }
    }
    else
    {
        apologize("Couldn't check for user cash.");
    }

    // SQL to check if user already have shares
    $sql = "SELECT * FROM portfolio WHERE uid='$uid' and symbol='$symbol'";

    // get results
    $result = mysql_query($sql);

    // data found
    if ($result)
    {
        //SQL update portfolio OR insert row
        if (mysql_num_rows($result) == 0)
            $sql = "INSERT INTO portfolio (uid, symbol, shares) VALUES ('$uid', '$symbol', $shares)";
        else
            $sql = "UPDATE portfolio SET shares = shares + $shares WHERE uid='$uid' and symbol='$symbol'";

        // execute update/delete
        $result = mysql_query($sql);

        // update successfull, proceed with updating cash for user
        if (!$result)
            apologize("Couldn't buy shares.");

        //record buy operation on history
        $sql = "INSERT INTO history (uid, symbol, shares, value, operation, datetime)"
             . "VALUES ('$uid', '$symbol', '$shares', '$s->price', 'B', NOW())";
        $result = mysql_query($sql);

        redirect("index.php");
    }
    else
    {
        apologize("Unable to buy shares.");
    }
?>

