#include <stdlib.h>
#include <stdio.h>
#include <string.h>

typedef struct s_node{
    char name[50];
    int id;
    struct s_node *next;
} node;


node *head;

void addNode();
void addTxtList(int id, char *name2);
void printList();
void freeMemory (node *head);
void deleteID();
void deleteName(node *head);
void findPosition(node *head);

int main(){

    int choice = 0;

    head = NULL;

    FILE * ifp = fopen("LinkedListInput.txt", "r");

    char inputInitial[50];
    char *name;
    char *name2;
    char *idNum;
    int id;

    //add members of text file to linked list
    while (fgets(inputInitial, 50, ifp) ){
        name = strtok(inputInitial, ",");
        idNum = strtok(NULL, " ,");
        id = atoi(idNum);

        addTxtList(id, name);
    }

    fclose(ifp);

    //char a[50];

    while (choice != 5) {
        printf("Make your selection: \n");
        printf("1. Add\n2. Print\n3. Delete by ID\n4. Delete by name\n5. Exit\n");
        scanf("%d", &choice);
        fflush(stdin);

    switch (choice) {
        case 1:
            addNode();
            break;

        case 2:
            printList(head);
            break;

        case 3:
            //findPosition(head);
            deleteID(head);
            break;

        case 4:
            //printf("%s\n", head->name);
            deleteName(head);

            break;

        case 5:
            printf("Exiting...");
            break;

        default:
            printf("You have not selected any option.\n");
        }
    }

    freeMemory(head);

    return 0;
}

void addNode(){
    char nameInput[50];
    char *name2;
    int idInput;

    printf("Enter a name: ");
    fgets(nameInput, 13, stdin);
    name2 = strtok(nameInput, "\n");

    printf("Enter an ID: ");
    scanf("%d", &idInput);
    fflush(stdin);

    node *temp = malloc(sizeof(node));

    strcpy(temp->name, nameInput);
    temp->id = idInput;
    temp->next = NULL;

    if (head != NULL);
        temp->next = head;

    head = temp;

    return;
}

void deleteID(){
    int n;

    printf("\nEnter an ID to be deleted: ");
    scanf("%d", &n);
    fflush(stdin);

    node *temp;

    if (n == head->id){
        printf("\nDelete first node?\n\n");
        temp = head;
        head = head->next;
        free(temp);

        return;
    }


    temp = head;
    node *prev = NULL;

    while (temp != NULL){
        if (temp->id == n){
            if (prev == NULL){
                temp = temp->next;
                free(head);
            }
            else {
                prev->next = temp->next;
                free(temp);
                temp = prev->next;
            }
        }
        else {
            prev = temp;
            temp = temp->next;
        }
    }

    printf("\n");

    return;
}

void printList(){

    node *temp = head;
    while (temp != NULL){
        printf("\nName: %s\nID: %d", temp->name, temp->id);
        printf("\n");
        temp = temp->next;
    }

    printf("\n\n");
    return;
}

void freeMemory (node *head){
    node *freeNode = head;
    node *holdNode = NULL;

    while (freeNode != NULL){
        holdNode = freeNode->next;
        free(freeNode);
        freeNode = holdNode;
    }
    return;
}

void addTxtList(int id, char *name2){

    node *temp = malloc(sizeof(node));

    strcpy(temp->name, name2);
    temp->id = id;
    temp->next = NULL;

    if (head != NULL);
        temp->next = head;

    head = temp;

    return;
}

void deleteName(node *head){

    char nameDel[50];
    char *name;

    //printf("\n%s*\n", head->name);

    printf("\nEnter a name to be deleted: ");
    fgets(nameDel, 50, stdin);
    name = strtok(nameDel, "\n");

    node *temp;
    //strcpy(temp->name, name);

    if (strcmp(head->name, name) == 0){
        printf("\nDelete first node?\n\n");
        temp = head;
        //strcpy(head->name, temp->name);
		if(head->next != NULL)
		{
			head = head->next;
	        free(temp);
		}
		if(head->next == NULL) 
		{
			free(head);
			free(temp);
		}

        return;
    }

    temp = head;
    node *prev = NULL;


    while (temp != NULL){
        printf("\nTemp Name: %s", temp->name);
        printf("\nSearching for: %s\n", name);

        if (strcmp(temp->name, name) == 0){
            printf("FOUND\n");
            if (prev == NULL){
                temp = temp->next;
                free(head);
            }
            else {
                prev->next = temp->next;
                free(temp);
                temp = prev->next;
            }
        }
        else {
            prev = temp;
            temp = temp->next;
        }
    }

    return;
}

void findPosition(node *head){

    node *temp;

    if (head->next == NULL){
        printf("\n\n1\n");
    }

    temp = head;

    while (temp->next != NULL){
        temp = temp->next;
    }
    printf("\nFound last node.");
    printf("\n%s %d", temp->name, temp->id);

    return;
}

