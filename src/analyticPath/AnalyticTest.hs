import FunExp
import Prelude hiding ((+), (-), (*), (/), negate, recip, (^), pi, sin, cos, exp, root)

instance Additive REAL where
    zero   = 0
    (+)    = (Prelude.+)
instance Multiplicative REAL where
    one    = 1
    (*)    = (Prelude.*)
instance AddGroup REAL where
    neg = (zero Prelude.-)
instance MulGroup REAL where
    recip  = (one Prelude./)
instance Algebraic REAL where
    root   = sqrt
instance Transcendental REAL where
    pi     = Prelude.pi
    sin    = Prelude.sin
    cos    = Prelude.cos
    exp    = Prelude.exp