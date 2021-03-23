{-# LANGUAGE GADTs, ConstraintKinds #-} 

import Prelude hiding ((+), (-), (*), (/), negate, recip, (^), pi, sin, cos, exp, root)
import Transcendental

data FunExp a where
    Zero    :: FunExp a
    One     :: FunExp a
    Con    :: (Transcendental a) => a -> FunExp a
    FunX   :: FunExp a
    Add    :: FunExp a -> FunExp a -> FunExp a
    Mul    :: FunExp a -> FunExp a -> FunExp a
    Negate :: FunExp a -> FunExp a
    Recip  :: FunExp a -> FunExp a
    Root   :: FunExp a -> FunExp a
    Pi     :: FunExp a
    Sin    :: FunExp a -> FunExp a
    Cos    :: FunExp a -> FunExp a
    Exp    :: FunExp a -> FunExp a

-- Class Instances --
-----------------------------------------------------------------------
instance Additive a => Additive (FunExp a) where
    zero   = Zero
    (+)   = Add
instance Multiplicative a => Multiplicative (FunExp a) where
    one    = One
    (*)   = Mul
instance AddGroup a => AddGroup (FunExp a) where
    neg    = Negate
instance MulGroup a => MulGroup (FunExp a) where
    recip  = Recip
instance Algebraic a => Algebraic (FunExp a) where
    root   = Root
instance Transcendental a => Transcendental (FunExp a) where
    pi     = Pi
    sin    = Sin
    cos    = Cos
    exp    = Exp

d :: Transcendental a => FunExp a -> FunExp a
d (Con _)       = zero
d (FunX)        = one
d (Add a b)     = (+) (d a) (d b)
d (Mul a b)     = (+) (a * d b) (d a * b)
d (Negate a)    = neg (d a)
d (Recip a)     = (neg $ recip (a * a)) * (d a)    --1/f(x) = -¹/f(x)² * f'(x)
d (Root a)      = (recip $ (root a * two)) * (d a)  --f(x)¹ᐟ² = ¹/(2f(x)) * f'(x)
d (Pi)          = zero
d (Sin a)       = (cos a) * (d a)
d (Cos a)       = (neg $ sin a) * (d a)
d (Exp a)       = (exp a) * (d a)
d _             = zero
