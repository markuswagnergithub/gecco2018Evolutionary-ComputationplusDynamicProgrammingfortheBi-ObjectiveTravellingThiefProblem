function [out]=bestreward(inputfolder, filename)
% disp(filename);
fid = fopen(strcat(inputfolder, '/', filename));
tline = fgetl(fid); %get the first line
ttpfile = tline;
% disp(ttpfile);
tline = fgetl(fid);

bestweight = 0;
bestbenefit = - Inf;
besttour = ttpfile;
tour = ttpfile;

b=[];

while ischar(tline)
    strline = strtrim(string(tline));
    if endsWith(strline, 'tour') || endsWith(strline, 'reversed')
%         disp(bestbenefit);
        b = [b, bestbenefit];
        bestbenefit = - Inf;
%         disp(strline);
        tour = strline;
        
        tline = fgetl(fid);
        continue
    end
    
    if startsWith(strline, 'weight') || strlength(strline) == 0
        tline = fgetl(fid);
        continue
    end
    
    d = str2double(strsplit(strline));
    tline = fgetl(fid);
    
    if d(2) > bestbenefit
       bestbenefit = d(2);
       besttour = tour;
       bestweight = d(1);
    end
    
end

% disp(bestbenefit);
b = [b, bestbenefit];

fclose(fid);

out = b;

end

