-- for testing heathrow.hs
import System.Environment
import System.Random

main = do
    (a:args) <- getArgs
    let numSections = read a
    genRandomPath numSections
    
genRandomPath :: Int -> IO ()
genRandomPath n = do
    gen <- getStdGen
    let rnds :: [Int]
        rnds = take (n*3) $ randomRs (1,1000) gen
    mapM_ print rnds
