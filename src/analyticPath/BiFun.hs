module BiFun where
type Bi     a = (a,a)
type BiFun  a = Bi (a->a)

biZero :: Additive a => Bi a
biZero  = (zero, zero)

biAdd :: Additive a => Bi a -> Bi a -> Bi a
biAdd (f, f') (g, g') = (f + g, f' + g')

biNegate :: AddGroup a => Bi a -> Bi a
biNegate f = biZero - f

biOne :: Multiplicative a => Bi a
biOne = (one, one)

biMul :: MulGroup a => 

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

