#include <stdio.h>

typedef struct point {
	float x;
	float y;
} point;

void swap(point *v, point *u)
{
	point aux = *v;
	*v = *u;
	*u = aux;
}

// Otra posibilidad más ineficiente
/*
void swap(point *v, point *u)
{
	int aux = v->x;
	v->x = u->x;
	u->x = aux;

	aux = v->y;
	v->y = u->y;
	u->y = aux;
}
*/

int main(int argc, char **argv) 
{
	point v = {.x = 9.7, .y = 10.98};
	point u = {.x = -500.99, .y = 302.6};
	printf("---Antes del swap---\n");
	printf("v.x=%f v.y=%f\n", v.x, v.y);
	printf("u.x=%f u.y=%f\n", u.x, u.y);

	swap(&v, &u);
	printf("\n---Despues del swap---\n");
	printf("v.x=%f v.y=%f\n", v.x, v.y);
	printf("u.x=%f u.y=%f\n", u.x, u.y);
}
