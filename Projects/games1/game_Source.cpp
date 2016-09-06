#include <iostream>
#include <iomanip>
#include <ctime>
using namespace std;

int cannon;
int grenade;
int enWeaponRange;
int enWeapon;
int enDamageMin;
int enDamageMax;
int HealthLoss;
int enHealthLoss;
int enDamageRange;
int damageRange;
int num;
int range;
int health;
int enemyHealth;
int countRound;
int ammo;
int enemyAmmo;
int damageMin;
int damageMax;
int weapon;



int main()
{
	health = 100;
	enemyHealth = 100;
	cannon = 1;
	grenade = 3;
	range = 2 - 1;
	
	while (health > 0 && enemyHealth > 0 )
	{
		countRound++;
		cout << "You are on round " << countRound << endl;
		cout << "Your weapon uses:" << endl;
		cout << "Rifle : unlimited." << endl;
		cout << "Cannon : " << cannon << endl;
		cout << "Grenade : " << grenade << endl;
		cout << "Please choose a weapon from the '1' for the cannon," << endl;
		cout << "'2' for the rifle or '3' for the grenade." << endl;
		cin >> weapon;
		if (weapon = 1 && cannon > 0) // This is the cannon.(Player)
		{
			ammo = 3;
			damageMin = 10;
			damageMax = 15;
			cannon--;
		}
		else if (weapon = 1 && cannon <= 0)
		{
		cout << "Please enter a valid response." << endl;
		continue;
		}
		if(weapon = 2) // This is the rifle.
		{
			ammo = 5;
			damageMin = 3;
			damageMax = 8;
		}
		if(weapon = 3 && grenade > 0) // This is the grenade.
		{
			ammo = 4;
			damageMin = 7;
			damageMax = 12;
			grenade--;
		}
		else if(weapon = 3 && grenade <= 0)
		{
		cout << "Please enter a valid response." << endl;
		continue;
		}
		enWeaponRange = 3 - 1;
		enWeapon = 1 + int(enWeaponRange * rand()/RAND_MAX +1.0);
		if (enWeapon == 1) // This is the cannon.(Computer)
		{
			enemyAmmo = 3;
			enDamageMin = 10;
			enDamageMax = 15;
		}
		else if(enWeapon == 2) // This is the rifle.
		{
			enemyAmmo = 5;
			enDamageMin = 3;
			enDamageMax = 8;
		}
		else if(enWeapon == 3) // This is the grenade.
		{
			enemyAmmo = 4;
			enDamageMin = 7;
			enDamageMax = 12;
		}
		damageRange = damageMax - damageMin;
		
		
		while (ammo > 0)
		{
			num = 1 + int(range * rand()/RAND_MAX +1.0);

			if (num = 1)
			{
				cout << "You attack the enemy." << endl;
				enHealthLoss = damageMin + int(damageRange * rand()/RAND_MAX +1.0);
				cout << "Enemy health lost is : " << enHealthLoss << endl;
				enemyHealth = enemyHealth - enHealthLoss;
				cout << "Enemy health remaining : " << enemyHealth << endl;
				--ammo;
				cout << "Ammo remaining : " << ammo << endl;
				system("PAUSE");
			}
			if (num = 2)
			{
				if(enWeapon == 1)
					cout << "Enemy weapon is Cannon." << endl;
				else if(enWeapon == 2)
					cout << "Enemy weapon is Rifle." << endl;
				else if(enWeapon == 3)
					cout << "Enemy weapon is Grenade." << endl;
				cout << "The enemy attacks you." << endl;
				HealthLoss = enDamageMin + int(enDamageRange * rand()/RAND_MAX +1.0);
				cout << "Health lost is : " << HealthLoss << endl;
				health = health - HealthLoss;
				cout << "Health remaining : " << health << endl;
				--enemyAmmo;
				cout << "Enemy ammo Remaining : " << enemyAmmo << endl;
				system("PAUSE");
			}
		}
	}
}

