import pygame
import random

pygame.init()

# Window
WIDTH = 400
HEIGHT = 440
CELL = 20

screen = pygame.display.set_mode((WIDTH, HEIGHT))
pygame.display.set_caption("Snake")

clock = pygame.time.Clock()

# Colors
dark_green = (42,166,44)
light1 = (165,252,97)
light2 = (151,222,88)
blue = (0,0,255)
darkBlue = (0,0,150)
red = (255,0,0)

rows = 20
cols = 18

# Snake
snake = [(2,8),(3,8),(4,8)]
direction = "right"
snake_length = 3

# Apple
apple = (12,8)

score = 0
best = 0

font = pygame.font.SysFont(None,30)

def draw_grid():
    count = 0
    for r in range(rows):
        for c in range(cols):
            x = r*CELL
            y = c*CELL + 40
            rect = pygame.Rect(x,y,CELL,CELL)

            if c == 0 or c == 17 or r == 0 or r == 19:
                pygame.draw.rect(screen,dark_green,rect)
            else:
                if count % 2 == 0:
                    pygame.draw.rect(screen,light1,rect)
                else:
                    pygame.draw.rect(screen,light2,rect)

            count += 1


def draw_snake():
    for s in snake:
        x = s[0]*CELL
        y = s[1]*CELL + 40
        pygame.draw.circle(screen,blue,(x+10,y+10),10)


def draw_apple():
    x = apple[0]*CELL
    y = apple[1]*CELL + 40
    pygame.draw.circle(screen,red,(x+10,y+10),9)


def move_snake():
    global snake_length, apple, score

    x,y = snake[-1]

    if direction == "right":
        x += 1
    elif direction == "left":
        x -= 1
    elif direction == "up":
        y -= 1
    elif direction == "down":
        y += 1

    new_head = (x,y)
    snake.append(new_head)

    if len(snake) > snake_length:
        snake.pop(0)

    if new_head == apple:
        score += 1
        snake_length += 1
        move_apple()


def move_apple():
    global apple
    while True:
        x = random.randint(1,18)
        y = random.randint(1,16)
        if (x,y) not in snake:
            apple = (x,y)
            break


def game_over():
    global snake, snake_length, direction, score, best, apple

    if score > best:
        best = score

    snake = [(2,8),(3,8),(4,8)]
    snake_length = 3
    direction = "right"
    apple = (12,8)
    score = 0


running = True

while running:

    clock.tick(8)

    for event in pygame.event.get():
        if event.type == pygame.QUIT:
            running = False

        if event.type == pygame.KEYDOWN:

            if event.key == pygame.K_RIGHT and direction != "left":
                direction = "right"

            elif event.key == pygame.K_LEFT and direction != "right":
                direction = "left"

            elif event.key == pygame.K_UP and direction != "down":
                direction = "up"

            elif event.key == pygame.K_DOWN and direction != "up":
                direction = "down"


    move_snake()

    head = snake[-1]

    if head[0] < 1 or head[0] > 18 or head[1] < 1 or head[1] > 16:
        game_over()

    if head in snake[:-1]:
        game_over()

    screen.fill((0,0,0))

    draw_grid()
    draw_snake()
    draw_apple()

    score_text = font.render(f"Apples: {score}",True,(255,255,255))
    best_text = font.render(f"Best: {best}",True,(255,255,255))

    screen.blit(score_text,(10,10))
    screen.blit(best_text,(10,25))

    pygame.display.update()

pygame.quit()
