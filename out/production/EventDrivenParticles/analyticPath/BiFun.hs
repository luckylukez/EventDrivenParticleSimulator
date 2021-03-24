{-# LANGUAGE GADTs, TypeSynonymInstances, FlexibleInstances #-}

module BiFun where

import Transcendental
import Prelude hiding ((+), (-), (*), (/), negate, recip, (^), pi, sin, cos, exp, root)

type Bi     a = (a,a)
type BiFun  a = Bi (a->a)

biZero :: Additive a => Bi a
biZero  = (zero, zero)

biAdd :: Additive a => Bi a -> Bi a -> Bi a
biAdd (f, f') (g, g') = (f + g, f' + g')

biNegate :: AddGroup a => Bi a -> Bi a
biNegate (f, f') = (neg f, neg f')

biOne :: Multiplicative a => Bi a
biOne = (one, one)

biMul :: Multiplicative a => Bi a -> Bi a -> Bi a
biMul (f, f') (g, g') = (f * g, f * g' + g * f')

biRecip :: MulGroup a => Bi a -> Bi a
biRecip (f, f') = (recip f, neg (recip (f*f)) * f')

biRoot :: Algebraic a => Bi a -> Bi a
biRoot (f, f') = (root f, f' * (recip ((one+one) * root f)))

biPi :: Transcendental a => Bi a
biPi = (pi, zero)

biSin :: Transcendental a => Bi a -> Bi a
biSin (f, f') = (sin f, f' * cos f )
biCos (f, f') = (cos f, neg (f' * sin f))
biExp (f, f') = (exp f, f' * exp f)


instance Additive a => Additive(Bi a) where
    zero  = biZero
    (+)  = biAdd
instance AddGroup a => AddGroup(Bi a) where
    neg   = biNegate
instance Multiplicative a => Multiplicative(Bi a) where
    one   = biOne
    (*)  = biMul    
instance MulGroup a => MulGroup(Bi a) where
    recip = biRecip
instance Algebraic a => Algebraic(Bi a) where
    root  = biRoot
instance Transcendental a => Transcendental(Bi a) where
    pi    = biPi
    sin   = biSin
    cos   = biCos
    exp   = biExp

