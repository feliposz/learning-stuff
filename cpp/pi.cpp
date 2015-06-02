#include <iostream>
#include <cmath>

double CalcPiLeibniz(int terms);
double CalcPiCircArc(int steps);

int main()
{
    std::cout << "Pi by the method of Leibniz: "
        << CalcPiLeibniz(1000000) << std::endl;
    std::cout << "Pi by the method of the circular arc: "
        << CalcPiCircArc(1000000) << std::endl;
    return 0;
}

double CalcPiLeibniz(int terms)
{
    double pi = 0.0;
    double div = 1.0;
    double signal = 1.0;

    for (int i = 0; i < terms; i++) {
        pi += signal / div;
        div += 2.0;
        signal = -signal;
    }

    return pi * 4.0;
}

double CalcPiCircArc(int steps)
{
    // a circle with radius = 2 has area = 4*pi
    // 1/4 of that area thus is pi
    double r = 2.0;
    double x = 0.0;
    double w = r / steps;
    double area = 0.0; // pi
    for (int i = 0; i < steps; i++) {
        double h = sqrt(r*r - x*x);
        area += h * w;
        x += w;
    }
    return area;
}
