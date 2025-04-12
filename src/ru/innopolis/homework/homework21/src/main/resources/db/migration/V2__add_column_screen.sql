ALTER TABLE public.tvs
    ADD screen VARCHAR(255);

ALTER TABLE public.tvs
    ALTER COLUMN screen SET NOT NULL;