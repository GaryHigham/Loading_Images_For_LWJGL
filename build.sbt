name := "Slick-Util (Part 1) - Loading Images for LWJGL"

version := "0.1"

seq(Slick2D.slickSettings: _*)

resourceDirectory in Compile <<= baseDirectory(_ / "resources") 

