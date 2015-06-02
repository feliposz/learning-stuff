<?

    // require common code
    require_once("includes/common.php"); 

    $symbol = mysql_real_escape_string(strtoupper($_POST["symbol"]));
    $uid = $_SESSION["uid"];

    // check if symbol was specified
    if ($symbol == "")
        apologize("Invalid symbol.");

    if (!preg_match("/^\d+$/", $_POST["shares"]))
        apologize("Invalid number of shares to sell.");
    else
        $shares = intval($_POST["shares"]);

    if ($shares <= 0)
        apologize("Can't sell a negative ammount of shares.");

    // SQL to get portfolio data
    $sql = "SELECT * FROM portfolio WHERE uid='$uid' and symbol='$symbol'";

    // get results
    $result = mysql_query($sql);

    // data found
    if (mysql_num_rows($result) == 1)
    {
        // grab row
        $row = mysql_fetch_array($result);
        $s = lookup($row["symbol"]);
        $current_shares = $row["shares"];

        //SQL update portfolio OR delete field
        if ($current_shares > $shares)
            $sql = "UPDATE portfolio SET shares = shares - $shares WHERE uid='$uid' and symbol='$symbol'";
        elseif ($current_shares == $shares)
            $sql = "DELETE FROM portfolio WHERE uid='$uid' and symbol='$symbol'";
        else
            apologize("Can't sell more shares than you have.");

        // execute update/delete
        $result = mysql_query($sql);

        // update successfull, proceed with updating cash for user
        if ($result)
        {
            //SQL to update user's cash
            $cash = $shares * $s->price;
            $sql = "UPDATE users SET cash = cash + $cash WHERE uid='$uid'";
            $result = mysql_query($sql);
            if (!$result)
            {
                apologize("Couldn't update cash for user.");
            }

            //record sell operation on history
            $sql = "INSERT INTO history (uid, symbol, shares, value, operation, datetime)"
                 . "VALUES ('$uid', '$symbol', '$shares', '$s->price', 'S', NOW())";
            $result = mysql_query($sql);
        }
        else
        {
            apologize("Couldn't sell shares.");
        }

        redirect("index.php");
    }
    else
    {
        apologize("Unable to sell shares.");
    }
?>

