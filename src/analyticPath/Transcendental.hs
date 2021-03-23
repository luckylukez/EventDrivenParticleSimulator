{-# LANGUAGE GADTs, ConstraintKinds, TypeSynonymInstances #-}

module Transcendental where 

import Prelude hiding ((+), (-), (*), (/), negate, recip, (^), pi, sin, cos, exp, root)

-- Class methods --
type Field a = (MulGroup a, AddGroup a)

class Additive a where
    zero  :: a
    (+)   :: a -> a -> a
 
class Additive a => AddGroup a where
    neg    :: a -> a

class AddGroup a => Multiplicative a where
    one   :: a
    (*)   :: a -> a -> a
 
class Multiplicative a => MulGroup a where
    recip  :: a -> a
 
class Field a => Algebraic a where
    root   :: a -> a
 
class Algebraic a => Transcendental a where
    pi     :: a
    sin    :: a -> a
    cos    :: a -> a
    exp    :: a -> a

-- Class shorthand functions ------------------------------------------
(/) :: Transcendental a => a -> a -> a
(/) a b = a * recip b 
(-) :: Transcendental a => a -> a -> a
(-) a b = a + neg b 
(^) :: Transcendental a => a -> Int -> a
(^) a b = foldr (*) one [a|_<-[1..b]]